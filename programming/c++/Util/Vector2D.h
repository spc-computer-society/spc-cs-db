#pragma once

#include <cmath>

#ifndef M_PI
#define M_PI 3.14159265358979323846
#endif

class Vector2D {
public:

	double x, y;

	Vector2D() :x(0), y(0) {}
	Vector2D(double x, double y) : x(x), y(y) {}
	Vector2D(const Vector2D& v) : x(v.x), y(v.y) {}

	Vector2D& operator=(const Vector2D& v)
	{
		x = v.x;
		y = v.y;
		return *this;
	}

	Vector2D operator+(const Vector2D& v)
	{
		return Vector2D(x + v.x, y + v.y);
	}
	Vector2D operator-(const Vector2D& v)
	{
		return Vector2D(x - v.x, y - v.y);
	}
	Vector2D operator*(const Vector2D& v)
	{
		return Vector2D(x * v.x, y * v.y);
	}
	Vector2D operator/(const Vector2D& v)
	{
		return Vector2D(x / v.x, y / v.y);
	}

	Vector2D& operator+=(const Vector2D& v)
	{
		x += v.x;
		y += v.y;
		return *this;
	}
	Vector2D& operator-=(const Vector2D& v)
	{
		x -= v.x;
		y -= v.y;
		return *this;
	}
	Vector2D& operator*=(const Vector2D& v)
	{
		x *= v.x;
		y *= v.y;
		return *this;
	}
	Vector2D& operator/=(const Vector2D& v)
	{
		x /= v.x;
		y /= v.y;
		return *this;
	}

	Vector2D operator+(double s)
	{
		return Vector2D(x + s, y + s);
	}
	Vector2D operator-(double s)
	{
		return Vector2D(x - s, y - s);
	}
	Vector2D operator*(double s)
	{
		return Vector2D(x * s, y * s);
	}
	Vector2D operator/(double s)
	{
		return Vector2D(x / s, y / s);
	}


	Vector2D& operator+=(double s)
	{
		x += s;
		y += s;
		return *this;
	}
	Vector2D& operator-=(double s)
	{
		x -= s;
		y -= s;
		return *this;
	}
	Vector2D& operator*=(double s)
	{
		x *= s;
		y *= s;
		return *this;
	}
	Vector2D& operator/=(double s)
	{
		x /= s;
		y /= s;
		return *this;
	}

    Vector2D& operator++()
    {
        x += 1.0;
        y += 1.0;
        return *this;
    }
    Vector2D& operator--()
    {
        x -= 1.0;
        y -= 1.0;
        return *this;
    }
    Vector2D operator++(int)
    {
        Vector2D tmp(x, y);
        x += 1.0;
        y += 1.0;
        return tmp;
    }
    Vector2D operator--(int)
    {
        Vector2D tmp(x, y);
        x -= 1.0;
        y -= 1.0;
        return tmp;
    }

	void Set(double x, double y)
	{
		this->x = x;
		this->y = y;
	}

	void Rotate(double deg)
	{
		double theta = deg / 180.0 * M_PI;
		double c = cos(theta);
		double s = sin(theta);
		double tx = x * c - y * s;
		double ty = x * s + y * c;
		x = tx;
		y = ty;
	}

	Vector2D& Normalize()
	{
		if (Magnitude() == 0) return *this;
		*this *= (1.0 / Magnitude());
		return *this;
	}

	Vector2D Ortho() const
	{
		return Vector2D(y, -x);
	}

	double Distance(const Vector2D& v) const
	{
		Vector2D d(v.x - x, v.y - y);
		return d.Magnitude();
	}

	double SqrMagnitude() const
	{
		return x * x + y * y;
	}
	double Magnitude() const
	{
		return std::sqrt(SqrMagnitude());
	}

	static double Dot(const Vector2D& v1, const Vector2D& v2)
	{
		return v1.x * v2.x + v1.y * v2.y;
	}
	static double cross(const Vector2D& v1, const Vector2D& v2)
	{
		return (v1.x * v2.y) - (v1.y * v2.x);
	}

	static Vector2D One()
	{
		return Vector2D(1, 1);
	}
	static Vector2D Zero()
	{
		return Vector2D(0, 0);
	}
	static Vector2D Up()
	{
		return Vector2D(0, 1);
	}
	static Vector2D Down()
	{
		return Vector2D(0, -1);
	}
	static Vector2D Left()
	{
		return Vector2D(-1, 0);
	}
	static Vector2D Right()
	{
		return Vector2D(1, 0);
	}
};
