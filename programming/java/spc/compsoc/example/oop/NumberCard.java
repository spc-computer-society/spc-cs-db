/*
 *     Comp-Soc-DB
 *     Copyright (C) 2020  Colin Chow
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package spc.compsoc.example.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A plain number card.
 * @author Colin
 */
public class NumberCard extends AbstractCard implements Card {
    public static final List<Card> defaults;
    static{
        defaults = Collections.synchronizedList(new ArrayList<>());
        Cards.register(defaults);
    }
    public NumberCard(Colour c, int number) {
        super(c, number);
        if(c.equals(DefaultColours.NONE)){
            throw new IllegalArgumentException("NONE initialized.");
        }
        name = number + Card.getColourString(c);
    }
    @Override
    public boolean isFunctional() {
        return false;
    }

    @Override
    public int getNumberPerDeck() {
        return defaults.size();
    }

    @Override
    public boolean isModded() {
        return false;
    }

    @Override
    public int pointValue() {
        return super.getNumber();
    }
    @Override
    public String getName(){
        return name;
    }

    @Override
    public String saveForm() {
        return Cards.getDefaultSaveForm(this);
    }
}
