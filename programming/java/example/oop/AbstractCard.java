/*
 *     SBA-ICT
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

package com.colin.games.uno.api.cards;

import java.util.Objects;

/**
 * <p>Basic implementation of Card interface.</p>
 * Implementation Specifications:
 * All subclasses of this class should include a public static final field
 * of type List{@literal <Subclass>} named defaults to be loaded by the loadDefaultMH()
 * private method of {@link com.colin.games.uno.api.Deck Deck}.
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

    /**
     * Constructs a new card from the {@link Card#saveForm() save format} specified.
     * All subclasses are <b> strongly recommended </b> to implement this constructor.
     * Otherwise, improperly constructed cards may result as this implementation has no 
     * knowledge of other information of other subclasses.
     * @param loadFrom The saved form to load the card from
     */
    public AbstractCard(String loadFrom){
        String[] tokens = loadFrom.split(",");
        colour = Colour.convertFromString(tokens[1]);
        number = Integer.parseInt(tokens[2]);
        name = generateName(tokens);
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

    /**
     * Generates a name according to the specified loading form at {@link Card#saveForm() loaded form} of this card.
     * @param tokens The tokens to generate the name of the card from
     * @return The name of the card
     */
    protected abstract String generateName(String[] tokens);
}
