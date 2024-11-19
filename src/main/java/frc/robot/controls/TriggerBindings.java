package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.RobotContainer;
import frc.robot.sensors.Bumper;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.RomiLED;

public abstract class TriggerBindings 
{
    private static CommandXboxController xbox;
    private static RomiDrivetrain romiDrivetrain;
    private static RomiLED greenLED;
    private static RomiLED redLED;
    private static Bumper frontBumper;
    private static DoubleSupplier leftYAxisSupplier;
    private static DoubleSupplier leftXAxisSupplier;

    private TriggerBindings()
    {}

    public static void createBindings(RobotContainer robotContainer)
    {
        xbox = robotContainer.getXbox();
        romiDrivetrain = robotContainer.getRomiDrivetrain();
        greenLED = robotContainer.getGreenLED();
        redLED = robotContainer.getRedLED();
        frontBumper = robotContainer.getFrontBumper();

        configSuppliers();

        configAButton();
        configFrontBumper();
        configDefaultCommands();
    }

    private static void configSuppliers()
    {
        leftYAxisSupplier = () -> -xbox.getRawAxis(1);
        leftXAxisSupplier = () -> -xbox.getRawAxis(0);
    }

    private static void configAButton()
    {
        Trigger aButtonTrigger = xbox.a();
        aButtonTrigger
            .whileTrue( Commands.runEnd( greenLED::on, greenLED::off ) );
    }

    private static void configFrontBumper()
    {
        Trigger frontBumperTrigger = new Trigger(frontBumper.isPressedSupplier());
        frontBumperTrigger
            .whileTrue(romiDrivetrain.onlyDriveBackwardCommand(leftYAxisSupplier));
    }

    private static void configDefaultCommands()
    {
        if(romiDrivetrain != null)
        {
            romiDrivetrain.setDefaultCommand(
                Commands.runEnd(
                    () -> romiDrivetrain.arcadeDrive(leftYAxisSupplier, leftXAxisSupplier),
                    () -> { romiDrivetrain.stopDrive(); System.out.println("Stopped"); },
                    romiDrivetrain
                )
                // MyCommands.arcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier)
                // romiDrivetrain.arcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier)
                // new ArcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier, romiDrivetrain)
                // Commands.runOnce(
                //     () -> romiDrivetrain.arcadeDrive(leftYAxisSupplier, leftXAxisSupplier), 
                //     romiDrivetrain
                // )
            );
        }
    }
}
