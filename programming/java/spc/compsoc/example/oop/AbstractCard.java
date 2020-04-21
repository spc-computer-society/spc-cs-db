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

import java.util.Objects;

/**
 * <p>Basic implementation of Card interface.</p>
 * @author Colin
 */
public abstract class AbstractCard implements Card {
    protected Colour colour;
    protected final int number;
    protected String name;
    public AbstractCard(Colour c, int number){
        colour = c;
        this.number = number;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof AbstractCard)){
            return false;
        }
        if(other == this){
            return true;
        }
        AbstractCard card = (AbstractCard) other;
        return colour.equals(card.colour) && number == card.number;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.colour);
        hash = 83 * hash + this.number;
        return hash;
    }

    @Override
    public String toString(){
        return getName();
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public Colour getColour(){
        return colour;
    }
    @Override
    public boolean canAccept(Card ac){
        return ac.getColour() == this.colour || ac.getNumber() == this.getNumber();
    }
    
    @Override
    public int getNumber(){
        return number;
    }
    @Override
    public int compareTo(Card o){
       int numCompare = Integer.compare(getNumber(), o.getNumber());
        if(numCompare == 0){
            return Colour.comparator().compare(this.colour, o.getColour());
        }
        return numCompare;
    }
}
