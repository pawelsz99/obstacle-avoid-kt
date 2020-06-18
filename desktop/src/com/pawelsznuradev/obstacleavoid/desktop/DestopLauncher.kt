package com.pawelsznuradev.obstacleavoid.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.pawelsznuradev.obstacleavoid.ObstacleAvoidGame
import com.pawelsznuradev.obstacleavoid.config.GameConfig

/**
 * @author Pawel Sznura 16/06/2020
 */

fun main() {
    val config =  LwjglApplicationConfiguration()
    config.width = GameConfig.WIDTH
    config.height = GameConfig.HEIGHT
    LwjglApplication(ObstacleAvoidGame(), config)


}