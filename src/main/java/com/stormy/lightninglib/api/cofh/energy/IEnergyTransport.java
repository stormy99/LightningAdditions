package com.stormy.lightninglib.api.cofh.energy;

import net.minecraft.util.EnumFacing;

/**
 * Implement this interface on Tile Entities which transport energy.
 *
 * This is used to "negotiate" connection types between two separate IEnergyTransports, allowing users to set flow direction and allowing for networks Of
 * IEnergyTransports to intelligently transfer energy to other networks.
 */
public interface IEnergyTransport extends IEnergyProvider, IEnergyReceiver {

	enum InterfaceType {
		/**
		 * Indicates that this {@link IEnergyTransport} is only sending power on this side.
		 */
		SEND, /**
		 * Indicates that this {@link IEnergyTransport} is only receiving power on this side.
		 */
		RECEIVE, /**
		 * Indicates that this {@link IEnergyTransport} wants to balance power between itself and the
		 * senders/receivers on this side. This is the default state.<br>
		 * To block any connection, use {@link IEnergyConnection#canConnectEnergy}
		 *
		 * IEnergyTransport based senders should check that the total power in the destination IEnergyTransport is less than the power in themselves before sending.
		 * <br>
		 * Active IEnergyTransport receivers (i.e., those that call {@link IEnergyProvider#extractEnergy}) should check that they contain less power than the
		 * source IEnergyTransport.
		 */
		BALANCE;

		/**
		 * Returns the opposite state to this InterfaceType.
		 *
		 * {@link #BALANCE} is considered its own opposite.<br>
		 * {@link #SEND} is the opposite of {@link #RECEIVE} and visa versa.
		 */
		public InterfaceType getOpposite() {

			return this == BALANCE ? BALANCE : this == SEND ? RECEIVE : SEND;
		}

		/**
		 * Returns the next InterfaceType as described in {@link IEnergyTransport#getTransportState}
		 */
		public InterfaceType rotate() {

			return rotate(true);
		}

		/**
		 * Returns the next InterfaceType as described in {@link IEnergyTransport#getTransportState}
		 *
		 * @param forward Whether to step in the order specified by {@link IEnergyTransport#getTransportState} (<tt>true</tt>) or to step in the opposite direction
		 */
		public InterfaceType rotate(boolean forward) {

			if (forward) {
				return this == BALANCE ? RECEIVE : this == RECEIVE ? SEND : BALANCE;
			} else {
				return this == BALANCE ? SEND : this == SEND ? RECEIVE : BALANCE;
			}
		}
	}

	/**
	 * {@inheritDoc}<br>
	 * This method <b>cannot</b> be a no-op for IEnergyTransport.
	 */
	@Override
	int getEnergyStored(EnumFacing from);

	InterfaceType getTransportState(EnumFacing from);

	/**
	 * This method is provided primarily for the purposes of automation tools, and should not need to be called by another IEnergyTransport.
	 *
	 * Calls to this method may fail if this IEnergyTransport has been secured by a user.
	 *
	 * @return Whether or not state was successfully altered.
	 */
	boolean setTransportState(InterfaceType state, EnumFacing from);

}
