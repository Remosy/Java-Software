### Q1
> Which is the class that has the “main” method that rPeANUt is run from?
- **Peanut**

### Q2
> Which class holds the state of the simulated computer (the memory, register, program counter, etc.)?
- **Simulate**

### Q3
> Which class has the code that assembles the program (takes the text of the program, generates the machine code and sets the memory for that program)? To work this out you may wish to follow the “Assemble” button, and see what code is executed when assemble is pressed.
- **Assemble**

### Q4
> Which class is responsible for simulating the instructions?
- **Simulator** [L144:assemble()]

### Q5
> How would your describe the design approach? (just a 1 or 2 sentence summary is expected)
- Model Vuew Controller

### Q6
> Which class is responsible for displaying the register’s values (this is the list on the right of the window)?
- **RegisterUI** [Simulator: L266 - L272]

### Q7
> How does this class for displaying a register value work? Which swing component is used? The register values are just 32 bit integers and they are displayed in their hex form, how is this achieved? What happens when you hover over the register? Can the user change the register values as you step through the simulator?
- For displaying value, **Simulator** adds the registers by casting each register to **RegisterUI** Class;
- In RegisterUI it has its own UI layout (Swing component: **JPanel--> JTextFiled**) for displaying value
- Each register's value is gotten from the **Word**
- Value (Int --> Hex) is calculated by in **Word** Class toString():
 ```
 String.format("0x%04x%04x", 0xffff & (value >> 16),
 					0xffff & value);
 ```
----

- Hover: show ToolTip, Dec & ASCii Value
- can change during step

### Stage 2
Added func in **Simulator**
**RegisterUI**
**RegisterTerm**
**Word**
