package com.central

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.central.assets.Images
import com.central.assets.Skins
import com.central.assets.Sounds
import com.central.assets.Tunes
import com.central.screens.Loading
import ktx.app.KtxGame

class App : KtxGame<Screen>() {

    val skinsManager = AssetManager()
    val textureManager = AssetManager()
    val soundsManager = AssetManager()
    val tunesManager = AssetManager()

    /**
     * this mess is to establish the spritebatches, cameras, and stages as member variables that get
     * initialized when create() is called and disposed in the dispose() method
     *
     * the main reason for this is because spritebatches, cameras, and stages each depend on the width and height,
     * which depend on Gdx.graphics - this is null until the create method gets called
     */
    private var width = 0f
    private var height = 0f

    private lateinit var sb: SpriteBatch
    private lateinit var hudSb: SpriteBatch

    private lateinit var cam: OrthographicCamera
    private lateinit var view: StretchViewport
    lateinit var stg: Stage

    private lateinit var hudCam: OrthographicCamera
    private lateinit var hudView: StretchViewport
    lateinit var hudStg: Stage

    override fun create() {

        Skins.manager = this.skinsManager
        Images.manager = this.textureManager
        Sounds.manager = this.soundsManager
        Tunes.manager = this.tunesManager

        this.width = Gdx.graphics.height.toFloat()
        this.height = Gdx.graphics.width.toFloat()

        this.sb = SpriteBatch()
        this.hudSb = SpriteBatch()

        this.cam = OrthographicCamera(this.width, this.height)
        this.view = StretchViewport(480f, 360f, this.cam)
        this.stg = Stage(this.view, this.sb)

        this.hudCam = OrthographicCamera(this.width, this.height)
        this.hudView = StretchViewport(480f, 360f, this.hudCam)
        this.hudStg = Stage(this.hudView , this.hudSb)

        /**
         * since this game doesn't have a lot to load, it's ok to load everything upfront
         * with more assets you'd want to strategically call load() only on what needs to be
         * ready for the next scene and unload the stuff you're done with
         *
         * the skin should be loaded first because it's used in the loading screen
         */
        Skins.values().forEach { it.load() }

        /**
         * load the skin before proceeding, it's needed for that label
         */
        while (!this.skinsManager.update()) this.skinsManager.update()

        Images.values().forEach { it.load() }
        Sounds.values().forEach { it.load() }
        Tunes.values().forEach { it.load() }

        val loading = Loading(this)
        addScreen(loading)
        setScreen<Loading>()
    }

    override fun dispose() {
        this.skinsManager.dispose()
        this.textureManager.dispose()
        this.soundsManager.dispose()
        this.tunesManager.dispose()
        println("all assets disposed")

        this.sb.dispose()
        this.hudSb.dispose()
        this.stg.dispose()
        this.hudStg.dispose()
        println("all spritebatches and stages disposed")

        super.dispose()
    }
}
