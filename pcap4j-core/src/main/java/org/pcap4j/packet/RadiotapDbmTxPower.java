/*_##########################################################################
  _##
  _##  Copyright (C) 2016  Pcap4J.org
  _##
  _##########################################################################
*/

package org.pcap4j.packet;

import org.pcap4j.packet.RadiotapPacket.RadiotapDataField;
import org.pcap4j.util.ByteArrays;

/**
 * Transmit power expressed as dBm (decibels from a 1 milliwatt reference).
 * This is the absolute power level measured at the antenna port.
 *
 * @see <a href="http://www.radiotap.org/defined-fields/dBm%20TX%20power">Radiotap</a>
 * @author Kaito Yamada
 * @since pcap4j 1.6.5
 */
public final class RadiotapDbmTxPower implements RadiotapDataField {

  /**
   *
   */
  private static final long serialVersionUID = -7046612192280202993L;

  private static final int LENGTH = 1;

  private final byte txPower;

  /**
   * A static factory method.
   * This method validates the arguments by {@link ByteArrays#validateBounds(byte[], int, int)},
   * which may throw exceptions undocumented here.
   *
   * @param rawData rawData
   * @param offset offset
   * @param length length
   * @return a new RadiotapDbmTxPower object.
   * @throws IllegalRawDataException if parsing the raw data fails.
   */
  public static RadiotapDbmTxPower newInstance(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    ByteArrays.validateBounds(rawData, offset, length);
    return new RadiotapDbmTxPower(rawData, offset, length);
  }

  private RadiotapDbmTxPower(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    if (length < LENGTH) {
      StringBuilder sb = new StringBuilder(200);
      sb.append("The data is too short to build a RadiotapDbmTxPower (")
        .append(LENGTH)
        .append(" bytes). data: ")
        .append(ByteArrays.toHexString(rawData, " "))
        .append(", offset: ")
        .append(offset)
        .append(", length: ")
        .append(length);
      throw new IllegalRawDataException(sb.toString());
    }

    this.txPower = ByteArrays.getByte(rawData, offset);
  }

  private RadiotapDbmTxPower(Builder builder) {
    if (builder == null) {
      throw new NullPointerException("builder is null.");
    }

    this.txPower = builder.txPower;
  }

  /**
   * @return txPower (unit: dBm)
   */
  public byte getTxPower() { return txPower; }

  /**
   * @return txPower (unit: dBm)
   */
  public int getTxPowerAsInt() { return txPower; }

  @Override
  public int length() {
    return LENGTH;
  }

  @Override
  public byte[] getRawData() {
    return ByteArrays.toByteArray(txPower);
  }

  /**
   * @return a new Builder object populated with this object's fields.
   */
  public Builder getBuilder() { return new Builder(this); }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[TX power: ")
      .append(txPower)
      .append(" dBm]");

    return sb.toString();
  }

  @Override
  public int hashCode() {
    return txPower;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) { return true; }
    if (!this.getClass().isInstance(obj)) { return false; }
    RadiotapDbmTxPower other = (RadiotapDbmTxPower) obj;
    return txPower == other.txPower;
  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 1.6.5
   */
  public static final class Builder {

    private byte txPower;

    /**
     *
     */
    public Builder() {}

    private Builder(RadiotapDbmTxPower obj) {
      this.txPower = obj.txPower;
    }

    /**
     * @param txPower txPower
     * @return this Builder object for method chaining.
     */
    public Builder txPower(byte txPower) {
      this.txPower = txPower;
      return this;
    }

    /**
     * @return a new RadiotapDbmTxPower object.
     */
    public RadiotapDbmTxPower build() {
      return new RadiotapDbmTxPower(this);
    }

  }

}
