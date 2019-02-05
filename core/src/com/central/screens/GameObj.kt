package com.central.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.FitViewport
import com.central.managers.GameManager
import com.central.managers.InputManager

object GameObj {
    val unitScale = 1f
    var paused = false
    var textbox = false
    val width = Gdx.graphics.width.toFloat()
    val height = Gdx.graphics.height.toFloat()
    val skin = Skin(Gdx.files.internal("custom/skin/skinui.json"))
    val sb = SpriteBatch()
    val sr = ShapeRenderer()

    val cam = OrthographicCamera(width, height)
    val hud = OrthographicCamera(width, height)
    val backgroundView = FitViewport(800f, 480f, cam)
    val view = FitViewport(800f, 480f, cam)
    val hudView = FitViewport(800f, 480f, hud)
    val stg = Stage(view, sb)
    val hudStg = Stage(hudView, sb)
    val backgroundStg = Stage(backgroundView, sb)

    val im = InputManager()
    val gm = GameManager()
}