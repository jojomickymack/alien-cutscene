package com.central.hudactors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.scenes.scene2d.Actor
import com.central.screens.GameObj


class ScreenBorder : Actor() {
    private var myRotation = 0.0f
    private val borderWidth = 25f * GameObj.unitScale
    private val squareWidth = 250f * GameObj.unitScale

    override fun act(delta: Float) {
        if (!GameObj.paused) myRotation += 1f
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.end()

        GameObj.sr.begin(ShapeType.Filled)

        GameObj.sr.color = Color.valueOf("abefcc")
        GameObj.sr.rectLine(0f, 0f, 0f, GameObj.hudStg.height, borderWidth)
        GameObj.sr.rectLine(0f, GameObj.hudStg.height, GameObj.hudStg.width, GameObj.hudStg.height, borderWidth)
        GameObj.sr.rectLine(GameObj.hudStg.width, 0f, GameObj.hudStg.width, GameObj.hudStg.height, borderWidth)
        GameObj.sr.rectLine(0f, 0f, GameObj.hudStg.width, 0f, borderWidth)
        GameObj.sr.end()

        GameObj.sr.begin(ShapeType.Line)
        GameObj.sr.translate(GameObj.hudStg.width, GameObj.hudStg.height, 0f)
        GameObj.sr.rotate(0f, 0f, 1f, myRotation)
        GameObj.sr.color = Color.WHITE
        GameObj.sr.rect(0f - squareWidth / 2, 0f - squareWidth / 2, squareWidth, squareWidth)
        GameObj.sr.identity()
        GameObj.sr.end()

        batch.begin()
    }
}