package frc.robot.sensors;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DigitalInput;

public class Bumper 
{
    private final DigitalInput bumper;

    public Bumper(int port)
    {
        bumper = new DigitalInput(port);
    }

    private boolean isPressed()
    {
        return !bumper.get();
    }

    public BooleanSupplier isPressedSupplier()
    {
        return this::isPressed;
    }
}
