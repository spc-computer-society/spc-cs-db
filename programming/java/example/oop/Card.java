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

package com.colin.games.uno.api.cards;

import com.colin.games.uno.api.Game;
import com.colin.games.uno.api.Savable;

import java.util.Optional;

/**
 * An abstract representation of card.
 * All cards must implement this interface.
 * @author Colin
 */
public interface Card extends Comparable<Card>,Savable {

    /**
     * Defines the action of a card on the card being played.
     * Optionally, returns a result of any arbitrary type.
     * @param <T> The type of the result, if any
     * @param context The game in which this card is played
     * @return The result generated
     */
    default <T> Optional<T> onUse(Game context){
        return Optional.empty();
    }

    /**
     * Tests if the card is functional.
     * @return If the card is functional
     */
    boolean isFunctional();

    /**
     * Returns the point value of this card.
     * @return The point value of the card
     */
    int pointValue();

    /**
     * Returns the number on this card.
     * If the card has no defined number, implementations are free to return
     * any negative value.
     * @return The number on this card, may be negative
     */
    int getNumber();

    /**
     * Gets the colour of the card.
     * If the card has no defined colour, use {@link Card.DefaultColours#NONE DefaultColours.NONE} instead.
     * @return The colour of the card
     */
    Colour getColour();

    /**
     * Returns the name of the given card
     * @return The name of the card
     */
    String getName();

    /**
     * Tests if the given card can be played on top of this card.
     * @param ac The card to test against
     * @return If this card can accept the given card to be on top
     */
    boolean canAccept(Card ac);

    /**
     * Gets the number of cards in each deck, by default.
     * @return How many times this card is in a default deck.
     */
    default int getNumberPerDeck(){
        return 10;
    }
    enum DefaultColours implements Colour{
        RED{
            @Override
            public String toString(){
                return "Red";   
            }
        },YELLOW{
            @Override
            public String toString(){
                return "Yellow";
            }
        },BLUE{
            @Override
            public String toString(){
                return "Blue";   
            }
        },GREEN{
            @Override
            public String toString(){
                return "Green";   
            }
        },NONE{
            @Override
            public String toString(){
                return "None";   
            }
        };
        @Override
        public String getName(){
            return toString();
        }
    }

    /**
     * Returns the default card for use.
     * @return The default card
     */
    default Card getDefault(){
        return Card.defaults();
    }

    /**
     * Returns a placeholder card.
     * All methods should not be called on this object.
     * Otherwise, an exception or undefined behaviour can result.
     * @return A placeholder card
     */
    static Card defaults(){
        return new Card() {
            @Override
            public boolean isFunctional() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public int pointValue() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public int getNumber() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public Colour getColour() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public String getName() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public boolean canAccept(Card ac) {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public int compareTo(Card o) {
                return -1;
            }

            @Override
            public boolean isModded() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }

            @Override
            public String saveForm() {
                throw new UnsupportedOperationException("Placeholder object used."); 
            }
            @Override
            public boolean isPlaceholder(){
                return true;
            }
        };
    }

    /**
     * Tests if the card is from a mod.
     * @return If this card is from a mod
     */
    boolean isModded();

    /**
     * Generates a string which can be appended to describe the colour of a card.
     * @param c The colour to get a description of
     * @return The formatted colour string
     */
    static String getColourString(Colour c){
        return (c == DefaultColours.NONE) ? "" : " of " + c.toString();
    }

    /**
     * Returns a String which will be saved to the save file.
     * <pre>
     * The string should uniquely identify the card instance.
     * It will be used to restore the card with a constructor.
     * The default form is:
     * ClassName,Colour,Number
     * Any other information can be appended by subclasses.
     * </pre>
     * @return The save form of this card to be written to a save file
     */
    @Override
    String saveForm();

    /**
     * Placeholder cards will only be used to substitute a card when the underlying card is undesirable.
     * They should not be used anywhere else.
     * @return If this card is a placeholder
     */
    default boolean isPlaceholder(){
        return false;
    }
}
