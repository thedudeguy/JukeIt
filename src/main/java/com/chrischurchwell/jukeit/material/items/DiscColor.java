/**
 * This file is part of JukeIt
 *
 * Copyright (C) 2011-2012  Chris Churchwell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.chrischurchwell.jukeit.material.items;


public interface DiscColor {

	public static final int BLACK		= 1;
	public static final int RED			= 2;
	public static final int GREEN 		= 3;
	public static final int BROWN 		= 4;
	public static final int BLUE 		= 5;
	public static final int PURPLE 		= 6;
	public static final int CYAN 		= 7;
	public static final int LIGHTGRAY 	= 8;
	public static final int GRAY 		= 9;
	public static final int PINK 		= 10;
	public static final int LIME 		= 11;
	public static final int YELLOW 		= 12;
	public static final int LIGHTBLUE 	= 13;
	public static final int MAGENTA 	= 14;
	public static final int ORANGE 		= 15;
	public static final int WHITE 		= 16;
	
	public int getColor();
	
}
