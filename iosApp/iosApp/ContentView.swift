import SwiftUI
import shared

enum CalcButton:String {
    case one = "1"
    case two = "2"
    case three = "3"
    case four = "4"
    case five = "5"
    case six = "6"
    case seven = "7"
    case eight = "8"
    case nine = "9"
    case zero = "0"
    case add = "+"
    case sub = "-"
    case division = "/"
    case multiply = "x"
    case equal = "="
    case clear = "ac"
    case decimal = "."
    case percent = "%"
    case negative = "+/-"
    
    var buttonColor: Color{
        switch self {
        case .add, .sub,.multiply, .division,.equal :
            return Color.orange
        case .clear, .negative, .percent:
            return Color(.lightGray)
        default:
            return Color(UIColor(red: 55/255.0, green: 55/255.0, blue: 55/255.0, alpha: 1))
        }
    }
}
enum Operation{
    case add, subtract, multiplication, division, equal, none
}
struct ContentView: View {
	let greet = Greeting().greeting()
    let sum = Calculator.Companion().sum(number1:10,number2:20)
    @State private var numberOne:String = ""
    private var numberTwo:String = ""
    @State private var value:String = "0"
    @State private var operation:Operation = .none
    
    let colors:[Color] = [.red,.orange,.yellow,.green,.blue, .purple]
    let buttons: [[CalcButton]]=[
        [.clear,.negative,.percent, .division],
        [.seven,.eight,.nine, .multiply],
        [.four,.five,.six, .sub],
        [.one,.two,.three, .add],
        [.zero,.decimal,.equal]
    ]
	var body: some View {
        ZStack{
            Color.black.ignoresSafeArea()
            VStack(spacing: 12){
                Spacer()
                HStack{
                    Spacer()
                    Text(value).bold().font(.system(size: 72)).foregroundColor(Color.white)
                }.padding()
                
                ForEach(buttons, id: \.self) { row in
                    HStack(spacing: 12) {
                        ForEach(row, id: \.self) { item in
                            Button(action: {
                                    didTapButton(button: item)
                            }, label:{
                                Text(item.rawValue).font(.system(size: 32)).frame(width: self.buttonWidth(item: item), height: self.buttonHeight(item: item), alignment: Alignment.center).background(item.buttonColor).foregroundColor(.white).cornerRadius(self.buttonHeight(item: item)/2)
                            })
                        }
                    }.padding(.bottom, 3)
                }
            }
        }
    }
	
    func didTapButton(button: CalcButton){
        switch button {
        case .add, .sub, .multiply,.division,.equal:
            if button == .add {
                self.operation = .add
                self.numberOne = self.value
                self.value = "0"
            } else if button == .sub {
                self.operation = .subtract
                self.numberOne = self.value
                self.value = "0"
            } else if button == .multiply {
                self.operation = .multiplication
                self.numberOne = self.value
                self.value = "0"
            } else if button == .division {
                self.operation = .division
                self.numberOne = self.value
                self.value = "0"
            } else if button == .equal {
                let runningNumberOne = Float(numberOne) ?? 0
                let runningNumberTwo = Float(self.value) ?? 0
                
                switch self.operation {
                case .add:
                    let sum = Calculator.Companion().sum(number1:runningNumberOne, number2:runningNumberTwo)
                    value = "\(sum)"
                case .subtract:
                    value = "\(Calculator.Companion().subtraction(number1: runningNumberOne, number2: runningNumberTwo))"
                case .multiplication:
                    value = "\(Calculator.Companion().multiplication(number1: runningNumberOne, number2: runningNumberTwo))"
                case .division:
                    do {
                        try
                            value = "\(Calculator.Companion().division(number1: runningNumberOne, number2: runningNumberTwo))"
                    }catch {
                        value = "\(error.localizedDescription)"
                    }
                    
                default:
                    debugPrint("Invalid operation")
                }
                self.operation = .equal
            }
                        
            break
        case .clear:
            value = "0"
            break
        case .negative, .percent:
            
            break
            
        
        default:
            let number = button.rawValue
            if self.operation == .equal{
                self.value = ""
                self.operation = .none
            }
            if(self.value == "0" && number != ".") {
                self.value = number
            }else {
                self.value  = "\(self.value)\(number)"
            }
        }
    }
    func buttonWidth(item: CalcButton)-> CGFloat{
       var width = ((UIScreen.main.bounds.width - (5*12)) / 4)
       if item == .zero{
           width = 2*width
       }
        debugPrint("Button Width:\(width), Screenwidth:\(UIScreen.main.bounds.width)")
        return width
    }
    func buttonHeight(item: CalcButton)-> CGFloat{
        return (UIScreen.main.bounds.width - (5*12)) / 4
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
