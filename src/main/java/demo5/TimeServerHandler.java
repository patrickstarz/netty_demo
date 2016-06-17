package demo5;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) { 
		ChannelFuture cf = ctx.writeAndFlush(new UnixTime());
		cf.addListener(ChannelFutureListener.CLOSE);
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}