package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

import server.config.Config;
import server.entity.Event;
import server.entity.User;
import server.event.handler.EventReceiverHandler;

/**
 * EventReceiverServer
 * This server opens a port and starts to listen for events and then
 * create event receiver handlers for each and every event request.
 * 
 * Also creates and starts an event consumer handler for delivering
 * the message. 
 */
public class EventReceiverServer extends Server implements Runnable {

  private final Logger logger = Logger.getLogger(this.getClass());
  private final int port = Config.EVENT_RECEIVER_SERVER_PORT;

  private PriorityBlockingQueue<Event> eventBlockingQueue;

  public EventReceiverServer(ConcurrentHashMap<Long, User> userMap, PriorityBlockingQueue<Event> eventBlockingQueue,
      ExecutorService pool, AtomicBoolean inService) {
    super(userMap, pool, inService);

    this.eventBlockingQueue = eventBlockingQueue;
  }

  @Override
  public void run() {
    try {
      setServerSocket(new ServerSocket(port));

      // Accept connection while serving.
      while (getInService().get()) {
        final Socket socket = getServerSocket().accept();

        // Handle the event.
        getPool().submit(new EventReceiverHandler(eventBlockingQueue, socket));
      }

      shutDownServer();

      logger.info("Event receiver server stops to serve on port: " + port);

    } catch (IOException e) {
      logger.error("There was something wrong while starting event receiver server.", e);
    }
  }

}