package demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 回显客户端发过来的数据
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//回显客户端发过来的数据
		ctx.write(msg);
		ctx.flush();   //这个是必须的，否则发送不过去
//		ch.writeAndFlush(msg);
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}