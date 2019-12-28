package frc.robot.commands.control;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Drive;

public class ArcadeDrive extends InstantCommand {
    private Drive kDrive;

    public ArcadeDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.DRIVETRAIN);
		this.kDrive = Robot.DRIVETRAIN;
    }

    // Called just before this Command runs the first time
	@Override
	protected void initialize() {
		kDrive.enableBrakeMode(true);
	}
    
    // Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	
		// Joystick configuration
		var throttle = -OI.joy.getY();
		var curve = OI.joy.getX();
		var zCurve = OI.joy.getZ();

		// Y Throttle control
		var posThrottle = Math.abs(throttle);
		var newThrottle = Math.copySign(deadband(posThrottle), throttle);

		// X Curve control
		var posCurve = Math.abs(curve);
		var newCurve = Math.copySign(approx(posCurve), curve);

		// Z Curve control
		var PosCurveZ = Math.abs(zCurve);
		var newCurveZ = 0.4 * Math.copySign(approx(PosCurveZ), zCurve);

		// Z Curve control
		if (curve < 0.9) {

			newCurve += newCurveZ;

		} else
		System.out.println(newThrottle + "   " + newCurve);
		kDrive.vbusArcade(newThrottle, newCurve);
		

		kDrive.sendDebugInfo();

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

	public double approx(double x) {   // ~1.3x slower than control group
        if (0 <= x && x < 0.1) {  // 1
            return 0;
        } else if (0.1<= x && x < 0.6) {  // 2
            return 0.4 * (x - 0.1);
        } else if (0.6 <= x && x < 0.8) {  // 3
            return 0.75 * (x - 0.33);
        } else if (0.8 <= x && x < 0.9) {  // 4
            return 1.5 * (x - 0.57);
        } else if (0.9 <= x && x < 0.95) {  // 5
            return 4 * (x - 0.78);
        } else if (0.95 <= x && x < 0.97) {  // 6
            return 5 * (x - 0.81);
        } else if (0.97 <= x && x <= 1) {  // 7
            return 6.66 * (x - 0.85);
        } else {
            System.out.println(x);
            throw new ArithmeticException("X needs to be between 0 and 1");
        }
	}
	
	public double deadband(double x) {
		if (0 <= x && x < 0.1) {
			return 0;
		} else {
			return (1.11 * x) - 0.11;
		}
	}
}


//  Make z axis output between ~.12 and 0