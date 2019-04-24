package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.scene2d.Scene2DSkin

object AppObj {

    var width = Gdx.graphics.height.toFloat()
    var height = Gdx.graphics.width.toFloat()

    val sb = SpriteBatch()
    val cam = OrthographicCamera(width, height)
    val view = StretchViewport(480f, 360f, cam)
    val stg = Stage(view, sb)

    val hudSb = SpriteBatch()
    val hudCam = OrthographicCamera(width, height)
    val hudView = StretchViewport(480f, 360f, hudCam)
    val hudStg = Stage(hudView , hudSb)

    val suspense = Gdx.audio.newSound(Gdx.files.internal("suspense.ogg"))
    val explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"))
    val alienSnd = Gdx.audio.newSound(Gdx.files.internal("roar.ogg"))

    val alienTex = Texture("alien-sprite.png")

    lateinit var app: App

    init {
        Scene2DSkin.defaultSkin = Skin(Gdx.files.internal("custom/skin/skinui.json"))
    }

    fun dispose() {
        sb.dispose()
        stg.dispose()

        hudSb.dispose()
        hudStg.dispose()

        suspense.dispose()
        explosion.dispose()
        alienSnd.dispose()

        alienTex.dispose()
    }
}