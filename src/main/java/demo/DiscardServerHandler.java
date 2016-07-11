package demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//处理1.此服务器不处理任何客户端发过来的数据
//		((ByteBuf) msg).release();
		
		//处理2.打印客户端发送过来的数据
		ByteBuf in = (ByteBuf) msg;
		try {
			String result = "";
			while (in.isReadable()) {
				result += (char)in.readByte();
			}
			System.out.println(result);
			System.out.flush();
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}