package com.central.managers

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.central.screens.GameObj


class InputManager {
    var uPressed = false
    var dPressed = false
    var lPressed = false
    var rPressed = false
    var aPressed = false
    var bPressed = false

    init {
        GameObj.hudStg.addListener(object : InputListener() {
            override fun keyDown(event: InputEvent, keycode: Int): Boolean {
                if (keycode == Input.Keys.UP) uPressed = true
                if (keycode == Input.Keys.DOWN) dPressed = true
                if (keycode == Input.Keys.LEFT) lPressed = true
                if (keycode == Input.Keys.RIGHT) rPressed = true
                if (keycode == Input.Keys.Z) aPressed = true
                if (keycode == Input.Keys.X) bPressed = true
                return true
            }

            override fun keyUp(event: InputEvent, keycode: Int): Boolean {
                if (keycode == Input.Keys.UP) uPressed = false
                if (keycode == Input.Keys.DOWN) dPressed = false
                if (keycode == Input.Keys.LEFT) lPressed = false
                if (keycode == Input.Keys.RIGHT) rPressed = false
                if (keycode == Input.Keys.Z) aPressed = false
                if (keycode == Input.Keys.X) bPressed = false
                return true
            }
        })
    }

    fun handleInput() {
//        if (lPressed) {
//            GameObj.gm.player.goLeft = true
//            GameObj.gm.player.goRight = false
//        } else if (rPressed) {
//            GameObj.gm.player.goLeft = false
//            GameObj.gm.player.goRight = true
//        } else if (!lPressed && !rPressed) {
//            GameObj.gm.player.goLeft = false
//            GameObj.gm.player.goRight = false
//        }
//        if (GameObj.textbox && aPressed) {
//            GameObj.gm.tw.remove()
//        }
    }
}