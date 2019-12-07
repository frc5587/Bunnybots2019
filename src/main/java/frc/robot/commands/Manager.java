package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.control.ArcadeDrive;

public class Manager extends Command {
    private Command currentRoutine;

    public Manager() {

    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        new ArcadeDrive().start();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        if (currentRoutine != null) {
            currentRoutine.cancel();
        }
    }

    @Override
    protected void interrupted() {
        end();
    }
}
