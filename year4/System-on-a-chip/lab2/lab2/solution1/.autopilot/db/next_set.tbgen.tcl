set moduleName next_set
set isTopModule 0
set isTaskLevelControl 1
set isCombinational 0
set isDatapathOnly 0
set isFreeRunPipelineModule 0
set isPipelined 0
set pipeline_type none
set FunctionProtocol ap_ctrl_hs
set isOneStateSeq 0
set ProfileFlag 0
set StallSigGenFlag 0
set isEnableWaveformDebug 1
set C_modelName {next_set}
set C_modelType { int 1 }
set C_modelArgList {
	{ permutations int 32 regular {array 7 { 2 2 } 1 1 } {global 2}  }
}
set C_modelArgMapList {[ 
	{ "Name" : "permutations", "interface" : "memory", "bitwidth" : 32, "direction" : "READWRITE", "bitSlice":[{"low":0,"up":31,"cElement": [{"cName": "permutations","cData": "int","bit_use": { "low": 0,"up": 31},"cArray": [{"low" : 0,"up" : 6,"step" : 1}]}]}], "extern" : 0} , 
 	{ "Name" : "ap_return", "interface" : "wire", "bitwidth" : 1} ]}
# RTL Port declarations: 
set portNum 17
set portList { 
	{ ap_clk sc_in sc_logic 1 clock -1 } 
	{ ap_rst sc_in sc_logic 1 reset -1 active_high_sync } 
	{ ap_start sc_in sc_logic 1 start -1 } 
	{ ap_done sc_out sc_logic 1 predone -1 } 
	{ ap_idle sc_out sc_logic 1 done -1 } 
	{ ap_ready sc_out sc_logic 1 ready -1 } 
	{ permutations_address0 sc_out sc_lv 3 signal 0 } 
	{ permutations_ce0 sc_out sc_logic 1 signal 0 } 
	{ permutations_we0 sc_out sc_logic 1 signal 0 } 
	{ permutations_d0 sc_out sc_lv 32 signal 0 } 
	{ permutations_q0 sc_in sc_lv 32 signal 0 } 
	{ permutations_address1 sc_out sc_lv 3 signal 0 } 
	{ permutations_ce1 sc_out sc_logic 1 signal 0 } 
	{ permutations_we1 sc_out sc_logic 1 signal 0 } 
	{ permutations_d1 sc_out sc_lv 32 signal 0 } 
	{ permutations_q1 sc_in sc_lv 32 signal 0 } 
	{ ap_return sc_out sc_lv 1 signal -1 } 
}
set NewPortList {[ 
	{ "name": "ap_clk", "direction": "in", "datatype": "sc_logic", "bitwidth":1, "type": "clock", "bundle":{"name": "ap_clk", "role": "default" }} , 
 	{ "name": "ap_rst", "direction": "in", "datatype": "sc_logic", "bitwidth":1, "type": "reset", "bundle":{"name": "ap_rst", "role": "default" }} , 
 	{ "name": "ap_start", "direction": "in", "datatype": "sc_logic", "bitwidth":1, "type": "start", "bundle":{"name": "ap_start", "role": "default" }} , 
 	{ "name": "ap_done", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "predone", "bundle":{"name": "ap_done", "role": "default" }} , 
 	{ "name": "ap_idle", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "done", "bundle":{"name": "ap_idle", "role": "default" }} , 
 	{ "name": "ap_ready", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "ready", "bundle":{"name": "ap_ready", "role": "default" }} , 
 	{ "name": "permutations_address0", "direction": "out", "datatype": "sc_lv", "bitwidth":3, "type": "signal", "bundle":{"name": "permutations", "role": "address0" }} , 
 	{ "name": "permutations_ce0", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "signal", "bundle":{"name": "permutations", "role": "ce0" }} , 
 	{ "name": "permutations_we0", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "signal", "bundle":{"name": "permutations", "role": "we0" }} , 
 	{ "name": "permutations_d0", "direction": "out", "datatype": "sc_lv", "bitwidth":32, "type": "signal", "bundle":{"name": "permutations", "role": "d0" }} , 
 	{ "name": "permutations_q0", "direction": "in", "datatype": "sc_lv", "bitwidth":32, "type": "signal", "bundle":{"name": "permutations", "role": "q0" }} , 
 	{ "name": "permutations_address1", "direction": "out", "datatype": "sc_lv", "bitwidth":3, "type": "signal", "bundle":{"name": "permutations", "role": "address1" }} , 
 	{ "name": "permutations_ce1", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "signal", "bundle":{"name": "permutations", "role": "ce1" }} , 
 	{ "name": "permutations_we1", "direction": "out", "datatype": "sc_logic", "bitwidth":1, "type": "signal", "bundle":{"name": "permutations", "role": "we1" }} , 
 	{ "name": "permutations_d1", "direction": "out", "datatype": "sc_lv", "bitwidth":32, "type": "signal", "bundle":{"name": "permutations", "role": "d1" }} , 
 	{ "name": "permutations_q1", "direction": "in", "datatype": "sc_lv", "bitwidth":32, "type": "signal", "bundle":{"name": "permutations", "role": "q1" }} , 
 	{ "name": "ap_return", "direction": "out", "datatype": "sc_lv", "bitwidth":1, "type": "signal", "bundle":{"name": "ap_return", "role": "default" }}  ]}

set RtlHierarchyInfo {[
	{"ID" : "0", "Level" : "0", "Path" : "`AUTOTB_DUT_INST", "Parent" : "",
		"CDFG" : "next_set",
		"Protocol" : "ap_ctrl_hs",
		"ControlExist" : "1", "ap_start" : "1", "ap_ready" : "1", "ap_done" : "1", "ap_continue" : "0", "ap_idle" : "1",
		"Pipeline" : "None", "UnalignedPipeline" : "0", "RewindPipeline" : "0", "ProcessNetwork" : "0",
		"II" : "0",
		"VariableLatency" : "1", "ExactLatency" : "-1", "EstimateLatencyMin" : "-1", "EstimateLatencyMax" : "-1",
		"Combinational" : "0",
		"Datapath" : "0",
		"ClockEnable" : "0",
		"HasSubDataflow" : "0",
		"InDataflowNetwork" : "0",
		"HasNonBlockingOperation" : "0",
		"Port" : [
			{"Name" : "permutations", "Type" : "Memory", "Direction" : "IO"}]}]}


set ArgLastReadFirstWriteLatency {
	next_set {
		permutations {Type IO LastRead 7 FirstWrite 4}}}

set hasDtUnsupportedChannel 0

set PerformanceInfo {[
	{"Name" : "Latency", "Min" : "-1", "Max" : "-1"}
	, {"Name" : "Interval", "Min" : "-1", "Max" : "-1"}
]}

set PipelineEnableSignalInfo {[
]}

set Spec2ImplPortList { 
	permutations { ap_memory {  { permutations_address0 mem_address 1 3 }  { permutations_ce0 mem_ce 1 1 }  { permutations_we0 mem_we 1 1 }  { permutations_d0 mem_din 1 32 }  { permutations_q0 mem_dout 0 32 }  { permutations_address1 MemPortADDR2 1 3 }  { permutations_ce1 MemPortCE2 1 1 }  { permutations_we1 MemPortWE2 1 1 }  { permutations_d1 MemPortDIN2 1 32 }  { permutations_q1 MemPortDOUT2 0 32 } } }
}
