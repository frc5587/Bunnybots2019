package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoShoot extends Command {
    private Timer timer = new Timer();

    public AutoShoot() {
        requires(Robot.SHOOT_BALL);
    }

    @Override
    protected void initialize() {
        timer.start();
        // Robot.DRIVETRAIN.enableBrakeMode(true);
        Robot.SHOOT_BALL.bestShoot(.75);
    }

    @Override
    protected boolean isFinished() {
        return timer.hasPeriodPassed(7);
    }

    @Override
    protected void end() {
        Robot.SHOOT_BALL.bestShoot(0);
        timer.stop();
    }
}