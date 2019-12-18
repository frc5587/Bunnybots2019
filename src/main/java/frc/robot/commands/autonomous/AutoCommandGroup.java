package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandGroup extends CommandGroup {

    public AutoCommandGroup() {
        addSequential(new AutoShoot());
        addSequential(new AutoForward());
    }

}