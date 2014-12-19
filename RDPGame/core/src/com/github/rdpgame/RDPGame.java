package com.github.rdpgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.github.rdpgame.screens.GameScreen;
import com.github.rdpgame.screens.SplashScreen;

public class RDPGame extends Game {
	public static final String VERSION = "0.0.0.03 Pre-Alpha";
	public static final String LOG = "RDPGame";
	
	FPSLogger log;

	@Override
	public void create () {
		log = new FPSLogger();
		//setScreen(new SplashScreen(this));
		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
		//log.log();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		return super.getScreen();
	}
}
