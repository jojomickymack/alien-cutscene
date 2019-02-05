package com.central.hudactors

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.central.screens.GameObj

/*

class OnScreenGamepad: Table() {
    private val buttonWidth = 100f
    private val buttonHeight = 100f

    private val aImg = Image(Texture("ctrl_a.png"))
    private val bImg = Image(Texture("ctrl_b.png"))
    private val rightImg = Image(Texture("ctrl_r.png"))
    private val leftImg = Image(Texture("ctrl_l.png"))

    init {
        aImg.setSize(buttonWidth, buttonHeight)
        bImg.setSize(buttonWidth, buttonHeight)
        rightImg.setSize(buttonWidth, buttonHeight)
        leftImg.setSize(buttonWidth, buttonHeight)

        this.row().pad(5f, 5f, 5f, 5f)
        this.add(aImg).size(aImg.width, aImg.height)
        this.add(bImg).size(bImg.width, bImg.height)
        this.add(leftImg).size(leftImg.width, leftImg.height).padLeft(GameObj.hudStg.width - aImg.width - bImg.width - leftImg.width - rightImg.width - 40f)
        this.add(rightImg).size(rightImg.width, rightImg.height)
        this.bottom().left()

        aImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                GameObj.im.aPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                GameObj.im.aPressed = false
            }
        })

        bImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                GameObj.im.bPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                GameObj.im.bPressed = false
            }
        })

        rightImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                GameObj.im.rPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                GameObj.im.rPressed = false
            }
        })

        leftImg.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                GameObj.im.lPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                GameObj.im.lPressed = false
            }
        })
    }
}

*/