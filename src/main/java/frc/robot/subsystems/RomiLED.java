package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RomiLED extends SubsystemBase
{
    private final DigitalOutput led;

    public RomiLED(int port)
    {
        led = new DigitalOutput(port);
    }

    public void on()
    {
        led.set(true);
    }

    public void off()
    {
        led.set(false);
    }

    public Command onCommand()
    {
        return runOnce( () -> on() );
    }

    public Command offCommand()
    {
        return runOnce( () -> off() );
    }
}
