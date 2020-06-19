package com.pawelsznuradev.obstacleavoid.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.pawelsznuradev.obstacleavoid.config.GameConfig
import com.pawelsznuradev.obstacleavoid.entity.Obstacle
import com.pawelsznuradev.obstacleavoid.entity.Player
import com.pawelsznuradev.obstacleavoid.utils.GdxArray
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

    private var obstacleTimer = 0f
    private val obstacles = GdxArray<Obstacle>()
    private var alive = true


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

        if (alive) {
            update(delta)
        }


        clearScreen()

        renderer.projectionMatrix = camera.combined

        renderer.use {
            player.drawDebug(renderer)
            obstacles.forEach { it.drawDebug(renderer) }
        }

        viewport.drawGrid(renderer)
    }

    private fun update(delta: Float) {
        player.update()
        blockPlayerFromLeavingWorld()

        updateObstacles()
        createNewObstacle(delta)

        if (isPlayerCollidingWithObstacles()) {
            alive = false
        }
    }

    private fun isPlayerCollidingWithObstacles(): Boolean {
        obstacles.forEach {
            if (it.isCollidingWith(player)) {
                return true
            }
        }
        return false
    }


    private fun createNewObstacle(delta: Float) {
        obstacleTimer += delta

        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            obstacleTimer = 0f

            //spawn
            val obstacleX = MathUtils.random(0f + Obstacle.BOUNDS_RADIUS, GameConfig.WORLD_WIDTH - Obstacle.BOUNDS_RADIUS)
            val obstacle = Obstacle()
            obstacle.setPosition(obstacleX, GameConfig.WORLD_HEIGHT)

            //add to array
            obstacles.add(obstacle)

        }
    }

    private fun updateObstacles() {
        obstacles.forEach { it.update() }
    }


    private fun blockPlayerFromLeavingWorld() {
/*        if(player.x < Player.HALF_SIZE){
            player.x = Player.HALF_SIZE
        }else if(player.x > GameConfig.WORLD_WIDTH - Player.HALF_SIZE){
            player.x = GameConfig.WORLD_WIDTH -Player.HALF_SIZE
        }*/

        player.x = MathUtils.clamp(player.x, Player.HALF_SIZE, GameConfig.WORLD_WIDTH - Player.HALF_SIZE)


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