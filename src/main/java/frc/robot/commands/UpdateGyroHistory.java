package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * UpdateGyroHistory
 */
public class UpdateGyroHistory extends Command {
    public UpdateGyroHistory() {
        setRunWhenDisabled(true);
    }

    @Override
    protected void execute() {
        Robot.DRIVETRAIN.updateGyroHistory();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}