/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.control;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class Shoot extends Command {

  public Shoot() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.SHOOTER);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double val = OI.xb.getY(Hand.kLeft);
    boolean buttonUnJam = OI.xb.getBumper(Hand.kLeft);
    boolean buttonSetSpeed = OI.xb.getBButton();
    boolean xButton = OI.xb.getXButton();

    if (buttonUnJam) {
      Robot.SHOOTER.setDeJammerSpeed(-.4);
    } else {
      Robot.SHOOTER.setDeJammerSpeed(0);
    }
    if (buttonSetSpeed) {
      Robot.SHOOTER.setShooterSpeed(.75);
    } else if (xButton) {
      Robot.SHOOTER.setShooterSpeed(1);
    } else {
      Robot.SHOOTER.setShooterSpeed(-val);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
