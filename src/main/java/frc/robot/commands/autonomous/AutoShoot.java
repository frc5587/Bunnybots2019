package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoShoot extends Command {
    private Timer timer = new Timer();

    public AutoShoot() {
        requires(Robot.SHOOTER);
    }

    @Override
    protected void initialize() {
        timer.start();
        Robot.SHOOTER.setShooterSpeed(.75);
    }

    @Override
    protected boolean isFinished() {
        return timer.hasPeriodPassed(7);
    }

    @Override
    protected void end() {
        Robot.SHOOTER.setShooterSpeed(0);
        timer.stop();
    }
}