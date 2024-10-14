package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RomiDrivetrain extends SubsystemBase
{
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);
    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor, rightMotor);

    public RomiDrivetrain()
    {}
}
