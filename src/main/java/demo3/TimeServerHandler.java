package demo3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	
	/**
	 * 不管客户端发过来什么信息都直接按自己的既定方式处理，所以不再使用channelRead()方法；
	 * the channelActive() method will be invoked when a connection is established and ready to generate traffic
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) { 
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		
		final ChannelFuture f = ctx.writeAndFlush(time); 
		f.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) {
				assert f == future;
				ctx.close();
			}
		});
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}