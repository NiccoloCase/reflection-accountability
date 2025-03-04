# Accountability Package

This project demonstrates the **Accountability** pattern as described in _Analysis Patterns_ by Martin Fowler, implemented in both Java and Python.

The implementation leverages the **reflection** pattern to separate the **operational level** from the **knowledge level**, ensuring greater flexibility and adaptability.

The concept of **Accountability** is tested through unit tests in a simple scenario involving entities such as Students, Professors, Full Professors, Universities, etc.

## Features

- **Support for subtyping**: The `Party` entity allows subtype extensions to accommodate different roles.
- **Constraint validation**: The `AccountabilityType` entity ensures that accountability relationships are valid.
- **Separation of levels**: The `Accountability` entity separates the operational level from the knowledge level.

## Diagram

Below is a high-level diagram representing the **Accountability** pattern implemented in this project:

![Accountability Diagram](images/accountability_diagram.png)
