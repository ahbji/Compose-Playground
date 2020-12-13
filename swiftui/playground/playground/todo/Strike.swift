//
//  Strike.swift
//  playground
//
//  Created by Suraj-GP on 12/12/20.
//

import SwiftUI

struct Strike: View {
    var body: some View {
        GeometryReader { geometry in
            Path { path in
                let size = geometry.size
                
                path.move(to: CGPoint(x: 0.0, y: 4.0))
                
                path.addLine(to: CGPoint(x: 0.0, y: size.height - 4.0))
                
                path.addArc(center: CGPoint(x: 4.0, y: size.height - 4.0), radius: 4.0, startAngle: .degrees(90), endAngle: .degrees(180), clockwise: false)
                
                path.addLine(to: CGPoint(x: size.width - 4.0, y: size.height))
                
                path.addArc(center: CGPoint(x: size.width - 4.0, y: size.height - 4.0), radius: 4.0, startAngle: Angle(degrees: 270.0), endAngle: Angle(degrees: 0.0), clockwise: false)
            }
            .size(width: 300.0, height: 100.0)
        }
    }
}

struct Strike_Previews: PreviewProvider {
    static var previews: some View {
        Strike()
    }
}
