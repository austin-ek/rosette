package org.chicagoedt.robocodeweb

import kotlin.browser.*
import org.chicagoedt.robocode.*
import org.w3c.dom.HTMLElement
import org.chicagoedt.robocodeweb.grid.*

/**
 * The driver to run a Grid canvas for the current game
 * @param game The game that the program is running
 */

class GridDriver(val game: Game){
    var tableBody = document.getElementById("grid")!!.children.item(0)!! as HTMLElement
    var level = game.currentLevel
    var gridTiles = arrayListOf<ArrayList<GridTile>>()
    private var tileWidth = 0

    /**
     * Calculates all of the necessary information when switching levels
     */
    fun calculateNewLevel(){
        val cNode = tableBody.cloneNode(false);
        tableBody.parentNode!!.replaceChild(cNode, tableBody);
        tableBody = cNode as HTMLElement

        level = game.currentLevel
        gridTiles.clear()

        for (y in 0 until level.properties.height){
            val row = arrayListOf<GridTile>()
            val tableRow = document.createElement("tr") as HTMLElement

            for (x in 0 until level.properties.width){
                val tile = GridTile(level, x, y)
                row.add(tile)
                tableRow.appendChild(tile.tableElement)
            }
            gridTiles.add(row)
            tableBody.prepend(tableRow)
        }

        tileWidth = gridTiles[0][0].element.getBoundingClientRect().width.toInt()

        calculatePlayers()
        handleResize()
    }

    /**
     * Calculates the positions for the players on the grid
     */
    fun calculatePlayers(){
        for ((name, player) in level.players){
            val x = player.x
            val y = player.y

            gridTiles[y][x].player = player
            gridTiles[y][x].refresh()
        }
    }

    /**
     * Refreshes the grid display
     */
    fun refresh(){
        for (row in gridTiles){
            for (tile in row){
                tile.player = null
                tile.refresh()
            }
        }

        calculatePlayers()
    }

    /**
     * Handles resizing of the window
     */
    fun handleResize(){
        tileWidth = gridTiles[0][0].element.getBoundingClientRect().width.toInt()
        for (row in gridTiles){
            for (tile in row){
                tile.element.style.height = tileWidth.toString() + "px"
                tile.element.style.fontSize = (tileWidth - 5).toString() + "px"
            }
        }
    }
}