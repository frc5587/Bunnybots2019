/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem {
  private TalonSRX intakeTalon;

  public Intake() {
    intakeTalon = new TalonSRX(RobotMap.Intake.INTAKE_TALON);
  }

  public void in() {
    intakeTalon.set(ControlMode.PercentOutput, 1);
  }

  public void out() {
    intakeTalon.set(ControlMode.PercentOutput, -1);
  }

  public void noIntake() {
    intakeTalon.neutralOutput();
  }

  @Override
  public void initDefaultCommand() {
    
  }
}
