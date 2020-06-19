package com.pawelsznuradev.obstacleavoid.utils.debug

import com.badlogic.gdx.Input
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue
import com.pawelsznuradev.obstacleavoid.utils.isKeyPressed
import com.pawelsznuradev.obstacleavoid.utils.logger
import com.pawelsznuradev.obstacleavoid.utils.toInternalFile

/**
 * @author Pawel Sznura 18/06/2020
 */
class DebugCameraConfig {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraConfig>()

        private const val MAX_ZOOM_IN = "maxZoomIn"
        private const val MAX_ZOOM_OUT = "maxZoomOut"
        private const val MOVE_SPEED = "moveSpeed"
        private const val ZOOM_SPEED = "zoomSpeed"

        private const val LEFT_KEY = "leftKey"
        private const val RIGHT_KEY = "rightKey"
        private const val UP_KEY = "upKey"
        private const val DOWN_KEY = "downKey"

        private const val ZOOM_IN_KEY = "zoomInKey"
        private const val ZOOM_OUT_KEY = "zoomOutKey"
        private const val RESET_KEY = "resetKey"
        private const val LOG_KEY = "logKey"

        private const val DEFAULT_LEFT_KEY = Input.Keys.A
        private const val DEFAULT_RIGHT_KEY = Input.Keys.D
        private const val DEFAULT_DOWN_KEY = Input.Keys.S
        private const val DEFAULT_UP_KEY = Input.Keys.W
        private const val DEFAULT_ZOOM_IN_KEY = Input.Keys.PAGE_UP
        private const val DEFAULT_ZOOM_OUT_KEY = Input.Keys.PAGE_DOWN
        private const val DEFAULT_ZOOM_RESET_KEY = Input.Keys.HOME
        private const val DEFAULT_LOG_KEY = Input.Keys.END


        private const val DEFAULT_MOVE_SPEED = 20f
        private const val DEFAULT_ZOOM_SPEED = 2f
        private const val DEFAULT_MAX_ZOOM_IN = 0.2f
        private const val DEFAULT_MAX_ZOOM_OUT = 10f

        private const val FILE_PATH = "debug/debugCameraConfig.json"

    }

    private var leftKey = DEFAULT_LEFT_KEY
    private var rightKey = DEFAULT_RIGHT_KEY
    private var upKey = DEFAULT_UP_KEY
    private var downKey = DEFAULT_DOWN_KEY

    private var zoomInKey = DEFAULT_ZOOM_IN_KEY
    private var zoomOutKey = DEFAULT_ZOOM_OUT_KEY
    private var resetKey = DEFAULT_ZOOM_RESET_KEY
    private var logKey = DEFAULT_LOG_KEY


    var maxZoomIn = DEFAULT_MAX_ZOOM_IN
        private set
    var maxZoomOut = DEFAULT_MAX_ZOOM_OUT
        private set
    var moveSpeed = DEFAULT_MOVE_SPEED
        private set
    var zoomSpeed = DEFAULT_ZOOM_SPEED
        private set


    private var fileHandle = FILE_PATH.toInternalFile()


    init {
        if (fileHandle.exists()) {
            load()
        } else {
            log.info("File does not exist, path= $FILE_PATH")
        }
    }

    fun isLeftPressed() = leftKey.isKeyPressed()
    fun isRightPressed() = rightKey.isKeyPressed()
    fun isUpPressed() = upKey.isKeyPressed()
    fun isDownPressed() = downKey.isKeyPressed()
    fun isZoomInPressed() = zoomInKey.isKeyPressed()
    fun isZoomOutPressed() = zoomOutKey.isKeyPressed()
    fun isResetPressed() = resetKey.isKeyPressed()
    fun isLogPressed() = logKey.isKeyPressed()

    private fun load() {
        try {
            val root = JsonReader().parse(fileHandle)

            maxZoomIn = root.getFloat(MAX_ZOOM_IN, DEFAULT_MAX_ZOOM_IN)
            maxZoomOut = root.getFloat(MAX_ZOOM_OUT, DEFAULT_MAX_ZOOM_OUT)
            moveSpeed = root.getFloat(MOVE_SPEED, DEFAULT_MOVE_SPEED)
            zoomSpeed = root.getFloat(ZOOM_SPEED, DEFAULT_ZOOM_SPEED)

            leftKey = getInputKeyValue(root, LEFT_KEY, DEFAULT_LEFT_KEY)
            rightKey = getInputKeyValue(root, RIGHT_KEY, DEFAULT_RIGHT_KEY)
            upKey = getInputKeyValue(root, UP_KEY, DEFAULT_UP_KEY)
            downKey = getInputKeyValue(root, DOWN_KEY, DEFAULT_DOWN_KEY)

            zoomInKey = getInputKeyValue(root, ZOOM_IN_KEY, DEFAULT_ZOOM_IN_KEY)
            zoomOutKey = getInputKeyValue(root, ZOOM_OUT_KEY, DEFAULT_ZOOM_OUT_KEY)
            resetKey = getInputKeyValue(root, RESET_KEY, DEFAULT_ZOOM_RESET_KEY)
            logKey = getInputKeyValue(root, LOG_KEY, DEFAULT_LOG_KEY)

            log.debug("camera config loaded from $FILE_PATH")


        } catch (e: Exception) {
            log.error("Error loading $FILE_PATH ", e)
            setupDefaults()
        }

    }

    private fun getInputKeyValue(jsonValue: JsonValue, name: String, defaultInputKey: Int): Int {
        val keyString = jsonValue.getString(name, Input.Keys.toString(defaultInputKey))
        return Input.Keys.valueOf(keyString)
    }

    private fun setupDefaults() {
        leftKey = DEFAULT_LEFT_KEY
        rightKey = DEFAULT_RIGHT_KEY
        upKey = DEFAULT_UP_KEY
        downKey = DEFAULT_DOWN_KEY

        zoomInKey = DEFAULT_ZOOM_IN_KEY
        zoomOutKey = DEFAULT_ZOOM_OUT_KEY
        resetKey = DEFAULT_ZOOM_RESET_KEY
        logKey = DEFAULT_LOG_KEY

        maxZoomIn = DEFAULT_MAX_ZOOM_IN
        maxZoomOut = DEFAULT_MAX_ZOOM_OUT
        moveSpeed = DEFAULT_MOVE_SPEED
        zoomSpeed = DEFAULT_ZOOM_SPEED

    }

    override fun toString(): String {
        return """  
        ${DebugCameraConfig::class.java.simpleName} {
            maxZoomIn = $maxZoomIn   
            maxZoomOut = $maxZoomOut   
            moveSpeed = $moveSpeed
            zoomSpeed = $zoomSpeed
            leftKey = ${Input.Keys.toString(leftKey)}
            rightKey = ${Input.Keys.toString(rightKey)}
            downKey = ${Input.Keys.toString(downKey)}
            upKey = ${Input.Keys.toString(upKey)}
            zoomInKey = ${Input.Keys.toString(zoomInKey)}
            zoomOutKey = ${Input.Keys.toString(zoomOutKey)}
            resetKey = ${Input.Keys.toString(resetKey)}
            logKey = ${Input.Keys.toString(logKey)}
        }""".trimIndent()
    }


}