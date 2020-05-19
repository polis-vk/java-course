package ru.mail.polis.patterns.structural.adapter;

/**
 *
 * Adapter class. Adapts the interface of the device ({@link FishingBoat}) into {@link RowingBoat}
 * interface expected by the client ({@link Captain}).
 *
 */
public class FishingBoatAdapter implements RowingBoat {

  private final FishingBoat boat;

  public FishingBoatAdapter(FishingBoat boat) {
    this.boat = boat;
  }

  @Override
  public void row() {
    boat.sail();
  }
}
