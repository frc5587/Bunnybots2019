package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoForward extends Command {
    private Timer timer = new Timer();

    public AutoForward() {
        requires(Robot.DRIVETRAIN);
    }

    @Override
    protected void initialize() {
        timer.start();
        Robot.DRIVETRAIN.driveForward(.3);
    }

    @Override
    protected boolean isFinished() {
        return timer.hasPeriodPassed(8);
    }

    @Override
    protected void end() {
        Robot.DRIVETRAIN.stop();
        timer.stop();
    }
}