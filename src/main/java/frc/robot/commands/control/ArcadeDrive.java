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

		kDrive.vbusArcade(throttle, curve);

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
}