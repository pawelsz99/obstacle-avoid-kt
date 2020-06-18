package com.pawelsznuradev.obstacleavoid.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.pawelsznuradev.obstacleavoid.config.GameConfig
import com.pawelsznuradev.obstacleavoid.entity.Player
import com.pawelsznuradev.obstacleavoid.utils.clearScreen
import com.pawelsznuradev.obstacleavoid.utils.debug.DebugCameraController
import com.pawelsznuradev.obstacleavoid.utils.drawGrid
import com.pawelsznuradev.obstacleavoid.utils.use

/**
 * @author Pawel Sznura 16/06/2020
 */
class GameScreen : Screen {


    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer
    private lateinit var player: Player
    private lateinit var debugCameraController: DebugCameraController


    override fun hide() {
        dispose()
    }

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()
        debugCameraController = DebugCameraController()
        debugCameraController.setStartPosotion(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y)

        player = Player()

        //calculate position of player
        val startPlayerX = GameConfig.WORLD_WIDTH / 2

        //position player
        player.setPosition(startPlayerX, 1f)

    }

    override fun render(delta: Float) {
        debugCameraController.handleDebugInput()
        debugCameraController.applyTo(camera)
        
        player.update()



        clearScreen()

        renderer.projectionMatrix = camera.combined

        renderer.use { player.drawDebug(renderer) }

        viewport.drawGrid(renderer)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        renderer.dispose()
    }
}