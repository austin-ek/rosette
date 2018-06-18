package org.chicagoedt.robocodeweb.sensorconfig

import jQuery
import org.chicagoedt.robocode.robots.RobotPosition
import org.chicagoedt.robocode.sensors.SensorType
import org.chicagoedt.robocodeweb.grid.PlayerTile
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.addClass

/**
 * The panel where sensors are dropped into to insert them into a side of a robot
 * @param position The side of the robot that this panel manages
 * @param playerTile The player that this robot manages
 * @param drawer The drawer to repopulate after taking a sensor from it
 */
class SensorPanel (val position : RobotPosition, val playerTile: PlayerTile, val drawer : SensorDrawer){
    val element = document.createElement("div") as HTMLElement

    init{
        element.addClass("sensorList")

        when(position){
            RobotPosition.FRONT -> element.addClass("frontSensorList")
            RobotPosition.BACK -> element.addClass("backSensorList")
            RobotPosition.LEFT -> element.addClass("leftSensorList")
            RobotPosition.RIGHT -> element.addClass("rightSensorList")
        }

        if (position == RobotPosition.LEFT || position == RobotPosition.RIGHT){
            element.addClass("sideSensorList")
        }

        addDroppable()
    }

    /**
     * Adds the necessary droppable properties to this panel
     */
    private fun addDroppable(){
        val drop = jQuery(element).asDynamic()
        drop.droppable()
        drop.droppable("option", "tolerance", "pointer")
        drop.droppable("option", "scope", "sensors")
        drop.droppable("option", "drop", ::onDrop)
        drop.droppable("option", "over", ::onOver)
        drop.droppable("option", "out", ::onOverOut)
    }

    /**
     * Called when a sensor is dropped into this panel
     * @param event The over event
     * @param ui The element being hovered
     */
    private fun onDrop(event : Event, ui : dynamic){
        element.style.boxShadow = ""
        val blockElement : HTMLElement = ui.draggable[0]
        val block : SensorBlock<*> = blockElement.asDynamic().block

        if (sensorTypeExists(block)) return
        if (!slotAvailable()) return

        playerTile.player.addSensorTo(position, block.sensor)

        element.appendChild(blockElement)
        drawer.populate()

    }

    /**
     * Called when a sensor is hovered over this panel
     * @param event The over event
     * @param ui The element being hovered
     */
    private fun onOver(event : Event, ui : dynamic){
        element.style.boxShadow = "0px 0px 10px grey"
    }

    /**
     * Called when a sensor is hovered out from over this panel
     * @param event The over event
     * @param ui The element being hovered
     */
    private fun onOverOut(event : Event, ui : dynamic){
        element.style.boxShadow = ""
    }

    /**
     * Checks if the a sensor with the same type is [block] is already in this panel
     * @return True if a sensor of the same type exists, false otherwise
     */
    private fun sensorTypeExists(block : SensorBlock<*>) : Boolean{
        val sensors = playerTile.player.getSensorsExceptEmpty(position)
        for (sensor in sensors){
            if (sensor.type == block.sensor.type) return true
        }
        return false
    }

    /**
     * Checks if there is a slot available in this panel
     * @return True if there is a slot available, false otherwise
     */
    private fun slotAvailable() : Boolean{
        val totalSensorSlots = playerTile.player.sensorCountAt(position)
        val usedSlots = playerTile.player.getSensorsExceptEmpty(position).size

        if (totalSensorSlots == usedSlots) return false
        else return true
    }
}