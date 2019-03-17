package com.central

import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.central.assets.Images
import com.central.assets.Sounds
import com.central.assets.Tunes
import com.central.screens.Game
import com.central.screens.Loading
import ktx.app.KtxGame

class App : KtxGame<Screen>() {

    val textureManager = AssetManager()
    val soundsManager = AssetManager()
    val tunesManager = AssetManager()

    override fun create() {
        AppObj.app = this

        Images.manager = textureManager
        Sounds.manager = soundsManager
        Tunes.manager = tunesManager

        /**
         * since this game doesn't have a lot to load, it's ok to load everything upfront
         * with more assets you'd want to strategically call load() only on what needs to be
         * ready for the next scene and unload the stuff you're done with
         */
        Images.values().forEach { it.load() }
        Sounds.values().forEach { it.load() }
        Tunes.values().forEach { it.load() }

        val loading = Loading(this)
        addScreen(loading)
        setScreen<Loading>()
    }

    override fun dispose() {
        textureManager.dispose()
        soundsManager.dispose()
        tunesManager.dispose()
        println("all assets disposed")
        super.dispose()
    }
}
