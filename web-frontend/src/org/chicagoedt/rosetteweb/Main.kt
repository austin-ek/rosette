package org.chicagoedt.rosetteweb

import org.w3c.dom.*
import kotlin.browser.*
import org.chicagoedt.rosette.*
import org.chicagoedt.rosette.robots.*
import org.chicagoedt.rosette.levels.*

/**
 * The game that the browser is running
 */
internal lateinit var game : Game

/**
 * The driver for the grid canvas
 */
private lateinit var gridDriver : GridDriver

/**
 * The driver for the editor area
 */
private lateinit var editorDriver : EditorDriver

/**
 * The configurator for the game
 */
private lateinit var configDriver : ConfigDriver

fun setup(robots : ArrayList<Robot>, levels : ArrayList<Level>){
    game = Game(levels, robots)
    game.attachEventListener(::update)
    window.onresize = {onResize()}
    if (document.readyState == DocumentReadyState.Companion.COMPLETE){
        onLoad()
    }
    else{
        window.onload = {onLoad()}
    }
}

@JsName("onLoad")
fun onLoad(){
    gridDriver = GridDriver(game)
    gridDriver.calculateNewLevel()

    editorDriver = EditorDriver(game, (document.getElementById("editor") as HTMLElement))
    editorDriver.calculateNewLevel()

    val header = document.getElementById("header") as HTMLElement
    header.innerHTML = game.currentLevel.properties.name
}

@JsName("onResize")
fun onResize(){
    //do nothing
}

/**
 * The main function to run when the page loads
 * @param args The arguments to run. Not currently used at all.
 */
fun main(args: Array<String>) {
    val windowType = js("typeof window")
    if (windowType != "undefined"){
        configDriver = ConfigDriver("config.xml", ::setup)
    }
}

fun getTypeOf(elem : Any) : String{
    return js("typeof elem")
}

/**
 * Updates according to the event from the game
 * @param e The event coming from the game
 */
fun update(e : Event){
    when (e){
        Event.LEVEL_UPDATE -> refresh()
        Event.LEVEL_VICTORY -> console.log("victory!")
    }
}

/**
 * Refreshes the display of the game
 */
fun refresh(){
    if (::gridDriver.isInitialized){
        gridDriver.refresh()
    }
}