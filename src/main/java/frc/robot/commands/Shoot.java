/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;

public class Shoot extends Command {

  public Shoot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.SHOOT_BALL);
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
    // Robot.SHOOT_BALL.bestShoot(-val);
    if (buttonUnJam) {
      Robot.SHOOT_BALL.jam(-.4);
    } else {
      Robot.SHOOT_BALL.jam(0);
    }
    if (buttonSetSpeed) {
      Robot.SHOOT_BALL.bestShoot(.75);
    } else if (xButton) {
      Robot.SHOOT_BALL.bestShoot(1);
    } else {
      Robot.SHOOT_BALL.bestShoot(-val);
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
