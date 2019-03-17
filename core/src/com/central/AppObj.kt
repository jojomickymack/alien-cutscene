package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ktx.assets.*

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

    lateinit var app: App

    init {

    }
}