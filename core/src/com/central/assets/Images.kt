package com.central.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import ktx.assets.getAsset
import ktx.assets.load

enum class Images {
    alien_egg,
    alien_logo,
    ship,
    alien_sprite,
    room,
    ripley,
    core,
    boom,
    space;

    val path = "images/${name}.png"
    fun load() = manager.load<Texture>(path)
    operator fun invoke() = manager.getAsset<Texture>(path)
    companion object {
        lateinit var manager: AssetManager
    }
}