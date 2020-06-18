package com.pawelsznuradev.obstacleavoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.pawelsznuradev.obstacleavoid.screen.GameScreen

/**
 * @author Pawel Sznura 16/06/2020
 */
class ObstacleAvoidGame : Game() {
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        setScreen(GameScreen())

    }
}