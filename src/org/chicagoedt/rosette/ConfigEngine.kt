package org.chicagoedt.rosette

import org.chicagoedt.rosette.Level.Level
import org.chicagoedt.rosette.Level.LevelProperties
import org.chicagoedt.rosette.Robots.RobotPlayer
import org.chicagoedt.rosette.Robots.Robot
import org.chicagoedt.rosette.Robots.RobotOrientation
import org.chicagoedt.rosette.Tiles.TileType


fun getRobots() : HashMap<String, Robot>{
    val robots = HashMap<String, Robot>()

    val surus = Robot("Surus", "", 1, 1)
    val hushpuppy = Robot("Hushpuppy", "", 1, 1)

    robots[surus.name] = surus
    robots[hushpuppy.name] = hushpuppy

    return robots
}

fun getLevels() : HashMap<String, Level>{
    val levels = HashMap<String, Level>()

    val robotPlayer1 = RobotPlayer("Surus", 5, 5, RobotOrientation.DIRECTION_UP)
    val robotPlayer2 = RobotPlayer("Hushpuppy", 3, 3, RobotOrientation.DIRECTION_UP)

    val list1 = HashMap<String, RobotPlayer>()
    val list2 = HashMap<String, RobotPlayer>()
    list1[robotPlayer1.name] = robotPlayer1
    list1[robotPlayer2.name] = robotPlayer2
    list2[robotPlayer2.name] = robotPlayer2

    val level1 = Level(LevelProperties("Level 1", 0, 10, 10), list1, arrayListOf("Surus", "Hushpuppy"))
    val level2 = Level(LevelProperties("Level 2", 0, 5, 5), list2, arrayListOf("Hushpuppy"))

    level1.makeGrid(arrayListOf(
            arrayListOf(TileType.VICTORY, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.OBSTACLE, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL)))

    level2.makeGrid(arrayListOf(
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.OBSTACLE, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL),
            arrayListOf(TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.NEUTRAL, TileType.VICTORY)))

    levels[level1.properties.name] = level1
    levels[level2.properties.name] = level2

    return levels
}

fun getLevelOrder() : ArrayList<String>{
    return arrayListOf("Level 1","Level 2")
}
