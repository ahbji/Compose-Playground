//
//  Globe.swift
//  playground
//
//  Created by Suraj-GP on 31/07/21.
//

import SwiftUI

let TWO_PI = 2 * CGFloat.pi
let PI = CGFloat.pi
let ONE_RADIAN = TWO_PI/360

struct Globe: View {
    
    var dots: [DotCoordinates]
    
    var inclinationAngle: CGFloat = 0
    
    // radian increment
    var granularity: CGFloat = 0.1
    
    @State var rotationY: CGFloat = 0
    
    let timer = Timer.publish(every: TimeInterval(1/2000), on: .main, in: .common)
        .autoconnect()
    
    var body: some View {
        
        GeometryReader { geometry in
            Path { path in
                
                let minDimension = min(geometry.size.width, geometry.size.height)
                
                let globeRadius = minDimension * 0.7
                
                let center = minDimension/2
                
                dots.forEach { dot in
                    
                    let x = globeRadius * sin(dot.azimuthAngle) * cos(dot.polarAngle)
                    let y = globeRadius * sin(dot.azimuthAngle) * sin(dot.polarAngle)
                    let z = globeRadius * cos(dot.azimuthAngle) - globeRadius
                    
                    let rotationX =
                        x * cos(rotationY + inclinationAngle) + (z + globeRadius) * sin(rotationY + inclinationAngle)
                    let rotationZ =
                        -x * sin(rotationY + inclinationAngle) + cos(rotationY + inclinationAngle) * (z + globeRadius) - globeRadius

                    let scale = minDimension / (minDimension - rotationZ)

                    let projectedX = (rotationX * scale)
                    let projectedY = (y * scale)

                    let rotatedX =
                        projectedX * cos(inclinationAngle) - projectedY * sin(inclinationAngle)
                    let rotatedY =
                        projectedX * sin(inclinationAngle) + projectedY * cos(inclinationAngle)
                    
                    path.addEllipse(in: CGRect(x: rotatedX + center, y: rotatedY + center, width: 5 * scale, height: 5 * scale))
                }
            }
            .onReceive(timer) { _ in
                self.rotationY += (self.granularity * ONE_RADIAN)
                if (self.rotationY > TWO_PI) {
                    self.rotationY = 0
                }
            }
            .frame(height: 400)
        }
    }
}

struct Globe_Previews: PreviewProvider {

    static var previews: some View {
        let randomDots = RandomDots()
        Globe(dots: randomDots.dots)
    }
}

struct DotCoordinates {
    let azimuthAngle: CGFloat
    let polarAngle: CGFloat
}

class RandomDots {
    
    var dots: [DotCoordinates] = []
    
    init() {
        for _ in 0..<1000 {
            let azimuthAngle = ((CGFloat.random(in: (0..<1)) * 2) - 1) * PI
            let polarAngle = CGFloat.random(in: (0..<1)) * TWO_PI
            dots.append(DotCoordinates(azimuthAngle: azimuthAngle, polarAngle: polarAngle))
        }
    }
}
