# Cell Society Critique

**Team Number:** 12

#### Shouldn't be part of the API
* Simulation.getStateToColorMap() - never actually used 
* Simulation.stopSimulation() - never used
* SpreadingFireSimulation.isOver() - never used

#### Should be part of the internal API
* public void calculateNewState() - used only in backend
* public boolean isOnFire() - used by cells to check on their neighbors
* GridFactory.generateGrid() - back end calling back end 
* GUISpeedSlider.getDisplay() - UI calling UI

#### Should be part of the external API
* DATA_FIELDS and DATA_CREDENTIALS final variables in Simulation
* All of the methods in the Simulation class (Simulation is basically the controller)
* SpreadingFireSimulation.SPREADING_FIRE_DATA_FIELDS - used by configuration and visualization
* SpreadingFireSimulation.getMyName() - used by config and visualization
* SpreadingFireSimulation.getPercentageFields() - used by config
* SpreadingFireSimulation.updateNeighbors() - used by visualization
* GUISpreadingFirePanel() - used by back end 
