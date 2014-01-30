package com.brokenneon.homeautomation;

import java.io.IOException;

import com.brokenneon.homeautomation.bean.House;
import com.brokenneon.homeautomation.parser.HouseParser;

public class ConnectedDAO {
	public static House getHouse() throws IOException {
		return HouseParser.parse(ConnectedUtils.getBatch());
	}
}

// class actionThread(threading.Thread):
// def __init__(self, device, action, level=None):
// threading.Thread.__init__(self)
// self.device = device
// self.action = action
// self.level = level
// def run(self):
// if self.action == "0":
// action(self.device['did'], "0")
// else:
// if not self.level:
// self.level = "100"
// turnLightOn(self.device, self.level)
//
//
//
//
//
//
// pp = pprint.PrettyPrinter(indent=4)
//
// root = "http://lighting.local./gwr/gop.php"
// rooms = {}
//
// def makeList(v):
// if isinstance(v, list):
// return v
// return [v]

// def action(device_id, action, type=''):
// query = {
// "cmd":"DeviceSendCommand",
// "data":(
// "<gip><version>1</version><token>1234567890</token><did>%s</did><value>%s</value>%s</gip>"
// % (device_id, action, type) ),
// "fmt":"xml"
// }
// return request(query)

// def setLevel(device_id, l):
// action(device_id, l, "<type>level</type>")
// # query = {
// # "cmd":"DeviceSendCommand",
// # "data":(
// "<gip><version>1</version><token>1234567890</token><did>%s</did><value>%s</value><type>level</type></gip>"
// % (device_id, level) ),
// # "fmt":"xml"
// # }
// # return request(query)

// # <?xml version="1.0"?>
// # <gip>
// # <version>1</version>
// # <token>1234567890</token>
// # <did>360101572923402708</did>
// # <value>0</value>
// # <type>level</type>
// # </gip>

// def getGateway():
// query = {
// "cmd":"GatewayGetInfo",
// "data":"<gip><version>1</version><token>1234567890</token><fwnew>1</fwnew></gip>",
// "fmt":"xml"
// }
// return request(query)

// def getBatch():
// query = {
// "cmd":"GWRBatch",
// "data":"<gwrcmds><gwrcmd><gcmd>RoomGetCarousel</gcmd><gdata><gip><version>1</version><token>1234567890</token><fields>name,image,imageurl,control,power,product,class,realtype,status</fields></gip></gdata></gwrcmd><gwrcmd><gcmd>UserGetListDefaultRooms</gcmd><gdata><gip><version>1</version><token>1234567890</token></gip></gdata></gwrcmd><gwrcmd><gcmd>UserGetListDefaultColors</gcmd><gdata><gip><version>1</version><token>1234567890</token></gip></gdata></gwrcmd></gwrcmds>",
// "fmt":"xml"
// }
// return request(query)

// def getRooms(batch):
// cmds = batch['gwrcmds']["gwrcmd"]
// for c in cmds:
// if c["gcmd"] == "RoomGetCarousel":
// return c["gdata"]["gip"]["room"]

// def getDevices(room):
// devices = room['device']
// return makeList(devices)

// def turnLightOn(device, newLevel):
// state = device.get('state', False)
// if not device.get('level',None):
// print "%s is unreachable" % device['name']
// return
// print "Setting %s to %s" % (device['name'], newLevel)
// if state == "0":
// setLevel( device['did'], 0 )
// action( device['did'], "1" )
// setLevel( device['did'], newLevel )
// else:
// setLevel( device['did'], newLevel )

// batch = getBatch()
// rooms = getRooms(batch)
// roomsByName = {}
// devicesById = {}
// for r in rooms:
// roomsByName[ r['name'] ] = r
// print "----------------------"
// print r['name']
// for d in getDevices(r):
// devicesById[d['did']] = d
// print " " + d['name']
// print "   state: " + d.get('state', '')
// print "   level: " + d.get('level', '')
// print "   did: " + d.get('did', '')
//
// #pp.pprint(batch)
// #print json.dumps(batch)
//
// #for item in batch:
// # print item
//
// usage = "usage: %prog [options] arg1 arg2"
// parser = OptionParser(usage=usage)
//
// parser.add_option("-a", "--action", dest="action",
// help="the action, either 1 or 0")
// parser.add_option("-l", "--level", dest="level",
// help="the level, between 0 and 100")
// parser.add_option("-d", "--device", dest="device",
// help="the device id to affect")
// parser.add_option("-r", "--room", dest="room",
// help="the name of the room to affect")
//
// (options, args) = parser.parse_args()
//
// if (options.action or options.level) and ( options.room or options.device ):
// devices = []
// if options.room:
// devices = getDevices( roomsByName[options.room] )
// elif options.device:
// devices = makeList(devicesById[options.device])
//
// for d in devices:
// if options.action == "0":
// actionThread(d, "0", None).start()
// else:
// level = 100
// if options.level:
// level = options.level
// actionThread(d, "1", level).start()
// else:
// parser.print_help()
//
