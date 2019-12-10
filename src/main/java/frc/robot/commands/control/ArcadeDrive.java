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
		// Xbox configuration
		// double throttle = -OI.xb.getY(Hand.kLeft);
		// double curve = OI.xb.getX(Hand.kLeft);

		// Joystick configuration
		var throttle = -OI.joy.getY();
		var curve = OI.joy.getX() * 0.85;

		kDrive.vbusArcade(throttle, curve);

		kDrive.sendDebugInfo();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}